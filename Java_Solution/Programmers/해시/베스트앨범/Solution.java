// 베스트앨범

import java.util.*;

class Solution {
    int N;
    Map<String, Integer> genreMap;
    List<Music> musicList;
    List<Integer> bestAlbum;
    
    public void makeHashMap(String[] genres, int[] plays) {
        for(int i=0; i<N; i++) {
            Music music = new Music(i, plays[i], genres[i]);
            musicList.add(music);
            
            if(!genreMap.containsKey(genres[i])) {
                genreMap.put(genres[i], plays[i]);
            } else {
                genreMap.put(genres[i], genreMap.get(genres[i]) + plays[i]);
            }
        }
        
        Collections.sort(musicList, new Comparator<Music>() {
            @Override
            public int compare(Music m1, Music m2) {
                if(m1.genre.equals(m2.genre)) {
                    if(m1.play == m2.play) {
                        return m1.num - m2.num;
                    }
                
                    return -(m1.play - m2.play);
                }
                
                return genreMap.get(m1.genre) - genreMap.get(m2.genre);
            }
        });
    }
    
    public void releaseAlbum() {
        
        while(genreMap.size() > 0) {
            String maxKey = "";
            int maxPlay = 0;
            
            for(String key: genreMap.keySet()) {
                if(maxPlay < genreMap.get(key)) {
                    maxPlay = genreMap.get(key);
                    maxKey = key;
                }
            }
            genreMap.remove(maxKey);

            int count = 0;
            while(true) {
                for(int i=0; i<musicList.size(); i++) {
                    if(count < 2 && musicList.get(i).genre.equals(maxKey)) {
                        bestAlbum.add(musicList.get(i).num);
                        count += 1;
                    }
                }
                break;
            }
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        N = genres.length;
        genreMap = new HashMap<>();
        musicList = new ArrayList<>();
        bestAlbum = new ArrayList<>();
        
        makeHashMap(genres, plays);
        releaseAlbum();
        
        int[] answer = new int[bestAlbum.size()];
        for(int i=0; i<answer.length; i++) {
            answer[i] = bestAlbum.get(i);
        }
        
        return answer;
    }
}

class Music {
    int num;
    int play;
    String genre;
    
    Music(int num, int play, String genre) {
        this.num = num;
        this.play = play;
        this.genre = genre;
    }
}