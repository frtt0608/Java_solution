// K번째 수
// array [1, 5, 2, 6, 3, 7, 4]
// commands [[2, 5, 3], [4, 4, 1], [1, 7, 3]]
// return [5, 6, 3]

import java.util.Arrays;

class Solution {
    public int built_in_fcn(int[] slice_array, int k) {
        Arrays.sort(slice_array);
        return slice_array[k-1];
    }
    
    public void Swap(int[] slice_array, int idx1, int idx2) {
        int temp = slice_array[idx1];
        slice_array[idx1] = slice_array[idx2];
        slice_array[idx2] = temp;
    }
    
    public int Dual_pivot_QuickSort(int[] slice_array, int Left, int Right, int k) {
        int pivot[] = new int[2];
        if(Left <= Right) {
            pivot = partition(slice_array, Left, Right);
            Dual_pivot_QuickSort(slice_array, Left, pivot[0]-1, k);
            Dual_pivot_QuickSort(slice_array, pivot[0]+1, pivot[1]-1, k);
            Dual_pivot_QuickSort(slice_array, pivot[1]+1, Right, k);
        }
        
        return slice_array[k-1];
    }
    
    public int[] partition(int[] slice_array, int Left, int Right) {
        int pivot[] = new int[2];
        if(slice_array[Left] > slice_array[Right]) 
            Swap(slice_array, Left, Right);
        
        int pivot1 = slice_array[Left];
        int pivot2 = slice_array[Right];
        
        int l = Left+1;
        int g = Right-1;
        int k = l;
        
        while(k <= g) {
            if(slice_array[k] < pivot1) {
                Swap(slice_array, k, l);   
                l++;
            } else {
                if(slice_array[k] > pivot2) {
                    while(k < g && pivot2 < slice_array[g]) {
                        g--;
                    }
                    Swap(slice_array, k, g);
                    g--;
                    
                    if(slice_array[k] < pivot1) {
                        Swap(slice_array, k, l);
                        l++;
                    }
                }
            }
            k++;
        }
        l--;
        g++;
        
        if(pivot1 > slice_array[l]) {
            Swap(slice_array, l, Left);
        }
        if(pivot2 < slice_array[g]) {
            Swap(slice_array, g, Right);
        }
        
        pivot[0] = l;
        pivot[1] = g;
        
        return pivot;
    }
    
    public int[] solution(int[] array, int[][] commands) {
        int i,j,k=0;
        int commands_length = commands.length;
        int answer[] = new int[commands_length];
        int slice_array[];
        
        for(int idx=0; idx<commands_length; idx++) {
            i = commands[idx][0];
            j = commands[idx][1];
            k = commands[idx][2];
            
            slice_array = Arrays.copyOfRange(array, i-1, j);
            System.out.println(Arrays.toString(slice_array));
            //answer[idx] = built_in_fcn(slice_array, k);
            answer[idx] = Dual_pivot_QuickSort(slice_array, 0, slice_array.length-1, k);
        }
        System.out.println(Arrays.toString(answer));
        return answer;
    }
}