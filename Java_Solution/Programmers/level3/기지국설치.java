public class 기지국설치 {

    static class Solution {
        static int installStationCount;
        static int distance;
        
        public static void installStation(int apartCount) {
            installStationCount += apartCount/distance;
            if(apartCount%distance > 0) {
                installStationCount += 1;
            }
        }
        
        public static void findUninstallStation(int n, int[] stations, int w) {
            int location = 1;
            
            for(int station: stations) {
                int start = station - w;
                int end = station + w;
                
                if(location < start) {
                    installStation(start - location);    
                }
                
                location = end + 1;
            }
            
            if(location <= n) {
                installStation(n - location + 1);
            }
        }
        
        public static int solution(int n, int[] stations, int w) {
            installStationCount = 0;
            distance = 2*w + 1;
            
            findUninstallStation(n, stations, w);
            
            return installStationCount;
        }
        
        public static void main(String[] args) {
            solution(11, new int[] {4,11}, 1);
        }
    }
}
