import java.util.*;

public class AIPlayer extends Player {

    // --- VARIABLES ---
    private List<Integer> plannedPieces = new ArrayList<>();
    private List<Integer> plannedDestinations = new ArrayList<>();
    private int[] fullDiceSequence = null; 
    private int turnCounter = 0;

    // --- CONFIGURATION ---
    // Beam Width: How many "best states" to track at once.
    // 500 is extremely safe for <15s runtime (usually takes <1 second).
    private static final int BEAM_WIDTH = 500; 

    public AIPlayer() { }

    @Override
    public int[] chooseMove(List<Integer> diceRoll, int[] currentPosition) {
        
        // 1. ONE-TIME SETUP: Load the dice sequence
        if (fullDiceSequence == null) {
            GameLoader loader = new GameLoader(GameMain.selectedLevel);
            // Load enough rolls to cover the game
            fullDiceSequence = new int[100]; 
            for (int i = 0; i < 30; i++) {
                fullDiceSequence[i] = loader.getDiceRoll(i);
            }
        }

        // 2. THE PLANNER: Run Beam Search if we have no plan
        if (plannedPieces.isEmpty()) {
            GameLoader loader = new GameLoader(GameMain.selectedLevel);
            int targetPiece = loader.getTargetPiece();
            
            System.out.println("AI: Starting Beam Search (Width " + BEAM_WIDTH + ")...");
            long start = System.currentTimeMillis();

            // Run the solver
            boolean found = solveWithBeamSearch(currentPosition, turnCounter, fullDiceSequence, targetPiece);
            
            long timeTaken = System.currentTimeMillis() - start;
            System.out.println("AI: Search complete in " + timeTaken + "ms. Solution found: " + found);
            
            if (!found) {
                System.out.println("AI: No win found within search depth. Defaulting to defensive Greedy.");
            }
        }

        // 3. EXECUTION: Follow the plan or Fallback
        int bestPiece = -1;
        int bestDest = -1;

        if (!plannedPieces.isEmpty()) {
            // EXECUTE PLAN
            bestPiece = plannedPieces.remove(0);
            bestDest = plannedDestinations.remove(0);
        } else {
            // FALLBACK (GREEDY)
            // Just in case Beam Search didn't find a win (e.g., game impossible or too long)
            return runGreedyFallback(diceRoll, currentPosition, new GameLoader(GameMain.selectedLevel).getTargetPiece());
        }

        // 4. APPLY MOVE to the board array
        if (bestPiece != -1) {
            currentPosition[bestPiece] = bestDest;
            for (int i = 1; i < 7; i++) {
                if (i != bestPiece && currentPosition[i] == bestDest) {
                    currentPosition[i] = -1; // Capture!
                }
            }
        }

        turnCounter++;
        return currentPosition;
    }

    // =========================================================
    //   CORE ALGORITHM: BEAM SEARCH
    // =========================================================
    private boolean solveWithBeamSearch(int[] startPieces, int startTurn, int[] diceSeq, int targetPiece) {
        
        // A "State" represents one possible board configuration in the future
        class State {
            int[] pieces;           // Position of all pieces
            List<Integer> pHist;    // History of pieces moved
            List<Integer> dHist;    // History of destinations
            int score;              // Lower is better

            State(int[] p, List<Integer> ph, List<Integer> dh) {
                this.pieces = p.clone();
                this.pHist = new ArrayList<>(ph);
                this.dHist = new ArrayList<>(dh);
                this.score = evaluate(p, targetPiece);
            }
            
            // HEURISTIC: Calculate how "good" this board is
            int evaluate(int[] p, int tPiece) {
                int tPos = p[tPiece];
                if (tPos == -1) return 100000; // Target dead = Worst possible score
                if (tPos == 0) return -1;      // Target at goal = Best possible score

                // Manhattan distance to (0,0)
                int row = tPos / 10;
                int col = tPos % 10;
                int dist = Math.max(row, col); 
                
                // Score = Distance * 100 (Primary factor)
                // Small penalty for total number of enemies alive (Secondary factor)
                int enemies = 0;
                for(int i=1; i<=6; i++) if (i != tPiece && p[i] != -1) enemies++;
                
                return (dist * 100) + enemies;
            }
        }

        // 1. Initial State
        List<State> currentBeam = new ArrayList<>();
        currentBeam.add(new State(startPieces, new ArrayList<>(), new ArrayList<>()));

        // 2. Loop through turns (Simulate up to 40 moves ahead)
        int maxDepth = 40; 
        for (int t = startTurn; t < startTurn + maxDepth; t++) {
            
            int dice = diceSeq[t];
            // Priority Queue keeps the best states at the top
            PriorityQueue<State> nextBeam = new PriorityQueue<>(Comparator.comparingInt(s -> s.score));
            Set<String> visitedStates = new HashSet<>(); // To avoid duplicate states

            boolean winFound = false;

            for (State s : currentBeam) {
                // If this state is already a win, stop!
                if (s.pieces[targetPiece] == 0) {
                    this.plannedPieces = s.pHist;
                    this.plannedDestinations = s.dHist;
                    return true;
                }

                // --- DETERMINE MOVABLE PIECES (Strict Rules) ---
                List<Integer> movable = new ArrayList<>();
                if (s.pieces[dice] != -1) {
                    // Rule: If rolled piece exists, YOU MUST MOVE IT.
                    movable.add(dice);
                } else {
                    // Rule: If rolled piece is dead, check next Higher then Lower (or vice versa based on variant)
                    // Standard strategy: Check both directions for nearest available
                    for (int i = dice + 1; i <= 6; i++) {
                        if (s.pieces[i] != -1) { movable.add(i); break; }
                    }
                    for (int i = dice - 1; i >= 1; i--) {
                        if (s.pieces[i] != -1) { movable.add(i); break; }
                    }
                }

                // --- EXPAND STATES ---
                for (int piece : movable) {
                    // Use GameState logic to get valid moves (grid boundaries, etc.)
                    List<Integer> moves = GameState.generatePossibleMoves(piece, s.pieces);
                    
                    for (int dest : moves) {
                        // Create New Board
                        int[] newPos = s.pieces.clone();
                        newPos[piece] = dest;
                        // Handle Captures
                        for (int i = 1; i < 7; i++) {
                            if (i != piece && newPos[i] == dest) newPos[i] = -1;
                        }

                        // Unique Key for Visited Set (Optimization)
                        String key = Arrays.toString(newPos);
                        if (visitedStates.contains(key)) continue;
                        visitedStates.add(key);

                        // Create New State
                        List<Integer> newPHist = new ArrayList<>(s.pHist);
                        List<Integer> newDHist = new ArrayList<>(s.dHist);
                        newPHist.add(piece);
                        newDHist.add(dest);

                        State nextState = new State(newPos, newPHist, newDHist);
                        nextBeam.add(nextState);
                        
                        // Trim Queue if too big (Memory optimization)
                        if (nextBeam.size() > BEAM_WIDTH + 100) {
                            // We can't easily remove the *worst* from a Min-PQ, 
                            // so we just let it grow slightly and trim in the next step.
                        }
                    }
                }
            }

            // --- PRUNING (The "Beam" part) ---
            // Only keep the top BEAM_WIDTH states for the next round
            currentBeam.clear();
            int count = 0;
            while (!nextBeam.isEmpty() && count < BEAM_WIDTH) {
                currentBeam.add(nextBeam.poll());
                count++;
            }

            // If beam is empty, we hit a dead end
            if (currentBeam.isEmpty()) break;
        }

        return false;
    }

    // =========================================================
    //   FALLBACK: SIMPLE GREEDY 
    // =========================================================
    private int[] runGreedyFallback(List<Integer> diceRoll, int[] currentPosition, int targetPiece) {
        // Simple logic: Move Target if possible, otherwise move whatever is safest
        int bestPiece = -1; 
        int bestDest = -1;
        double minScore = 9999.0;

        for (int piece : diceRoll) {
            for (int move : GameState.generatePossibleMoves(piece, currentPosition)) {
                double score = 0;
                // Prefer moving target
                if (piece == targetPiece) score -= 100;
                // Prefer moving towards 0,0
                score += (move/10 + move%10);
                
                if (score < minScore) {
                    minScore = score;
                    bestPiece = piece;
                    bestDest = move;
                }
            }
        }
        
        if (bestPiece != -1) {
            currentPosition[bestPiece] = bestDest;
            for(int i=1; i<7; i++) if (i!=bestPiece && currentPosition[i]==bestDest) currentPosition[i] = -1;
        }
        return currentPosition;
    }
}