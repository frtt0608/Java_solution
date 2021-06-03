import java.util.*;
import java.io.*;

public class Solution {
    static final int SIZE = 2000;
    static int N, totalEnergy;
    static PriorityQueue<CollisionInfo> pq;
    static List<Atom> atoms;

    static class CollisionInfo {
        double time;
        int atomIdx1, atomIdx2;

        CollisionInfo(double time, int atomIdx1, int atomIdx2) {
            this.time = time;
            this.atomIdx1 = atomIdx1;
            this.atomIdx2 = atomIdx2;
        }
    }

    static class Atom {
        int x, y;
        int dir;
        int energy;

        Atom(int y, int x, int dir, int energy) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.energy = energy;
        }
    }

    public static void risingAtom(int i, Atom atom1) {
        for(int j=i+1; j<N; j++) {
            Atom atom2 = atoms.get(j);

            if(atom1.y > atom2.y) {
                if(atom2.dir == 1 && atom1.x == atom2.x) {
                    pq.offer(new CollisionInfo((atom1.y - atom2.y)/2.0, i, j));

                } else if(atom2.dir == 2 && atom1.y - atom2.y == atom2.x - atom1.x) {
                    pq.offer(new CollisionInfo((atom1.y - atom2.y), i, j));

                } else if(atom2.dir == 3 && atom1.y - atom2.y == atom1.x - atom2.x) {
                    pq.offer(new CollisionInfo((atom1.y - atom2.y), i, j));
                }
            }
        }
    }

    public static void descendingAtom(int i, Atom atom1) {
        for(int j=i+1; j<N; j++) {
            Atom atom2 = atoms.get(j);
            
            if(atom1.y < atom2.y) {
                if(atom2.dir == 0 && atom1.x == atom2.x) {
                    pq.offer(new CollisionInfo((atom2.y - atom1.y)/2.0, i, j));

                } else if (atom2.dir == 2 && atom2.y - atom1.y == atom2.x - atom1.x) {
                    pq.offer(new CollisionInfo((atom2.y - atom1.y), i, j));

                } else if(atom2.dir == 3 && atom2.y - atom1.y == atom1.x - atom2.x) {
                    pq.offer(new CollisionInfo((atom2.y - atom1.y), i, j));
                }
            }
        }
    }

    public static void goingLeftAtom(int i, Atom atom1) {
        for(int j=i+1; j<N; j++) {
            Atom atom2 = atoms.get(j);
         
            if(atom1.x > atom2.x) {
                if(atom2.dir == 0 && atom1.x - atom2.x == atom2.y - atom1.y) {
                    pq.offer(new CollisionInfo((atom1.x - atom2.x), i, j));

                } else if(atom2.dir == 1 && atom1.x - atom2.x == atom1.y - atom2.y) {
                    pq.offer(new CollisionInfo((atom1.x - atom2.x), i, j));

                } else if(atom2.dir == 3 && atom1.y == atom2.y) {
                    pq.offer(new CollisionInfo((atom1.x - atom2.x)/2.0, i, j));
                }
            }
        }
    }

    public static void goingRightAtom(int i, Atom atom1) {
        for(int j=i+1; j<N; j++) {
            Atom atom2 = atoms.get(j);
            
            if(atom1.x < atom2.x) {
                if(atom2.dir == 0 && atom2.x - atom1.x == atom2.y - atom1.y) {
                    pq.offer(new CollisionInfo((atom2.x - atom1.x), i, j));

                } else if(atom2.dir == 1 && atom2.x - atom1.x == atom1.y - atom2.y) {
                    pq.offer(new CollisionInfo((atom2.x - atom1.x), i, j));

                } else if(atom2.dir == 2 && atom1.y == atom2.y) {
                    pq.offer(new CollisionInfo((atom2.x - atom1.x)/2.0, i, j));
                }
            }
        }
    }


    public static void searchCollisionAtoms() {
        Atom atom1;
        
        for(int i=0; i<N-1; i++) {
            atom1 = atoms.get(i);

            switch(atom1.dir) {
                case 0:
                    risingAtom(i, atom1);
                    break;
                case 1:
                    descendingAtom(i, atom1);
                    break;
                case 2:
                    goingLeftAtom(i, atom1);
                    break;
                case 3:
                    goingRightAtom(i, atom1);
            }
        }
    }

    public static void checkDestroyedAtoms() {
        double[] checkTime = new double[N];

        while(!pq.isEmpty()) {
            CollisionInfo info = pq.poll();

            if(checkTime[info.atomIdx1] == 0 && checkTime[info.atomIdx2] == 0) {
                checkTime[info.atomIdx1] = info.time;
                checkTime[info.atomIdx2] = info.time;
            } else if(checkTime[info.atomIdx1] == info.time) {
                checkTime[info.atomIdx2] = info.time;
            }
        }

        for(int i=0; i<N; i++) {
            if(checkTime[i] > 0) {
                totalEnergy += atoms.get(i).energy;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        Comparator<CollisionInfo> pqComp = new Comparator<CollisionInfo>() {
            @Override
            public int compare(CollisionInfo info1, CollisionInfo info2) {
                if(info1.time < info2.time) return -1;
                else if(info1.time > info2.time) return 1;
                else if(info1.atomIdx1 != info2.atomIdx1) return info1.atomIdx1 - info2.atomIdx1;
                else if(info1.atomIdx2 != info2.atomIdx2) return info1.atomIdx2 - info2.atomIdx2;
                return 0;
            }
        };

        for(int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());

            totalEnergy = 0;
            atoms = new ArrayList<>();
            pq = new PriorityQueue<>(pqComp);

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = SIZE + Integer.parseInt(st.nextToken());
                int y = SIZE - Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                int energy = Integer.parseInt(st.nextToken());

                atoms.add(new Atom(y, x, dir, energy));
            }

            searchCollisionAtoms();
            checkDestroyedAtoms();

            System.out.println("#" + tc + " " + totalEnergy);
        }
    }   
}