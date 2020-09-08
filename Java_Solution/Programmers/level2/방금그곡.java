import java.util.*;

class 방금그곡 {
    String answer, m;
    int playingTime;
    
    public String changeMelody(String melody) {
        melody = melody.replace("C#", "c");
        melody = melody.replace("D#", "d");
        melody = melody.replace("F#", "f");
        melody = melody.replace("G#", "g");
        melody = melody.replace("A#", "a");
        
        return melody;
    }
    
    public void getMelody(String[] musicinfo) {
        String all_melody = "";
        
        String[] start_time = musicinfo[0].split(":");
        String[] end_time = musicinfo[1].split(":");
        int time = Integer.parseInt(end_time[1]) - Integer.parseInt(start_time[1]) + 
            60*(Integer.parseInt(end_time[0]) - Integer.parseInt(start_time[0]));
        
        String[] melody = changeMelody(musicinfo[3]).split("");
        int t = 0;
        while(t < time) {
            all_melody += melody[t%melody.length];
            t += 1;
            
        }
        
        // System.out.println(all_melody);
        if(all_melody.contains(m)) {
            if(answer.equals("(None)")) {
                answer = musicinfo[2];
                playingTime = time;
            } else {
                // 재생시간이 긴 음악을 반환.
                if(playingTime < time) {
                    answer = musicinfo[2];
                    playingTime = time;
                }
            }
        }
    }
    
    public String solution(String m, String[] musicinfos) {
        answer = "(None)";
        this.m = changeMelody(m);
        playingTime = 0;
        
        if(musicinfos.length == 0)
            return answer;
        
        for(String musicinfo : musicinfos) {
            // System.out.println(Arrays.toString(musicinfo.split(",")));
            getMelody(musicinfo.split(","));
        }
        
        return answer;
    }
}