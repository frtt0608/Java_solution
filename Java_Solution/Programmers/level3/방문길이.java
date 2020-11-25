import java.util.*;

public class 방문길이 {

    static class Solution {
        static Set<Node> dirSet;
        static int robotX, robotY;
        static int[] dx = {0,1, 0,-1}, dy = {1,0,-1,0};
        static List<Character> dirList;
        
        public static boolean isWall(int x, int y) {
            if(x<-5 || x>5 || y<-5 || y>5) return true;
            return false;
        }
        
        public static void moveRobot(int dir) {
            int newRobotX = robotX + dx[dir];
            int newRobotY = robotY + dy[dir];
            
            if(isWall(newRobotX, newRobotY))
                return;
            
            dirSet.add(new Node(robotX, robotY, newRobotX, newRobotY));
            dirSet.add(new Node(newRobotX, newRobotY, robotX, robotY));
            
            robotX = newRobotX;
            robotY = newRobotY;
        }
        
        public static int solution(String dirs) {
            dirSet = new HashSet<>();
            dirList = Arrays.asList(new Character[] {'L', 'U', 'R', 'D'});
            robotX = 0;
            robotY = 0;
            
            for(char dir: dirs.toCharArray()) {
                moveRobot(dirList.indexOf(dir));
            }
            
            return dirSet.size()/2;
        }
        
        public static void main(String[] args) {
            solution("ULIRRDLLU");
        }
    }

    public static class Node {
        int x;
        int y;
        int newX;
        int newY;
        
        Node(int x, int y, int newX, int newY) {
            this.x = x;
            this.y = y;
            this.newX = newX;
            this.newY = newY;
        }
        
        @Override
        public int hashCode() {
            int prime = 31;
            int result = 0;
            
            result = result*prime + this.x;
            result = result*prime + this.y;
            result = result*prime + this.newX;
            result = result*prime + this.newY;
            
            return result;
        }
        
        @Override
        public boolean equals(Object obj) {
            Node n = (Node)obj;
            
            if(this.x == n.x && this.y == n.y && this.newX == n.newX && this.newY == n.newY) 
                return true;
            return false;
        }
    }
}
