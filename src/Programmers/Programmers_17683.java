package Programmers;

import java.util.ArrayList;

public class Programmers_17683 {
    public static void main(String[] args) {
        new Solution().solution("CC#BCC#BCC#BCC#B", new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"});
    }

    static class Solution {
        int musicAmount;
        Music[] musics;
        public String solution(String m, String[] musicinfos) {
            initialize(musicinfos);
            String answer = getAnswer(m);
            System.out.println(answer);
            return answer;
        }

        private void initialize(String[] musicinfos) {
            musicAmount = musicinfos.length;
            musics = new Music[musicAmount];
            for (int i = 0; i < musicAmount; i++) {
                musics[i] = new Music(musicinfos[i]);
            }
        }

        private String getAnswer(String m) {
            Music musicToFind = null;
            for (int musicNum = 0; musicNum < musicAmount; musicNum++) {
                Music music = musics[musicNum];
                if (music.contain(m)) {
                    if (musicToFind != null) {
                        if (music.isMorePriority(musicToFind)) {
                            musicToFind = music;
                        }
                    } else {
                        musicToFind = music;
                    }
                }
            }

            if (musicToFind == null) {
                return "(None)";
            } else {
                return musicToFind.title;
            }
        }

        private class Music {
            int startTime, endTime, playTime;
            String title;
            String[] chords;

            public Music(String musicinfo) {
                String[] info = musicinfo.split(",");
                startTime = transformTime(info[0]);
                endTime = transformTime(info[1]);
                playTime = endTime - startTime;
                title = info[2];
                chords = getChords(info[3]);
            }

            private int transformTime(String s) {
                String[] times = s.split(":");
                int hour = Integer.parseInt(times[0]);
                int min = Integer.parseInt(times[1]);
                return hour * 60 + min;
            }

            private String[] getChords(String chordStr) {
                ArrayList<String> chords = new ArrayList<>();

                for (int i = 0; i < chordStr.length(); i++) {
                    StringBuilder chord = new StringBuilder();
                    chord.append(chordStr.charAt(i));
                    if (i < chordStr.length() - 1 && chordStr.charAt(i + 1) == '#') {
                        chord.append(chordStr.charAt(++i));
                    }
                    chords.add(chord.toString());
                }

                return chords.toArray(new String[0]);
            }

            public boolean contain(String m) {
                String[] memoryChord = getChords(m);
                String[] playedChords = getPlayedChords();

                for (int start = 0; start <= playedChords.length - memoryChord.length; start++) {
                    for (int matched = 0; matched < memoryChord.length; matched++) {
                        if (!memoryChord[matched].equals(playedChords[start + matched])) {
                            break;
                        }

                        if (isMatched(memoryChord, matched)) {
                            return true;
                        }
                    }
                }
                return false;
            }

            public String[] getPlayedChords(){
                String[] playedChords = new String[playTime];

                int time = 0;
                int index = 0;
                while (time < playTime) {
                    playedChords[time++] = chords[index++];

                    if (index >= chords.length) {
                        index = 0;
                    }
                }

                return playedChords;
            }

            private boolean isMatched(String[] memoryChord, int matched) {
                return matched == memoryChord.length - 1;
            }

            public boolean isMorePriority(Music otherMusic) {
                return this.playTime > otherMusic.playTime;
            }
        }
    }
}
