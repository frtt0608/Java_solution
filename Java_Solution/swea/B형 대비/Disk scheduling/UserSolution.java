public class UserSolution {
  int[] table;
  int head,track_size,head_pos;
  int table_idx;
  Boolean[] v = new Boolean[MAX];
	
	private static int MAX = 100000;
	
	public void init(int track_size, int head){
    // TO DO
    this.table_idx = 0;
    this.track_size = track_size;
    this.table = new int[track_size];
    this.head = head;
    this.head_pos = head;
	}
	
	public void request(int track){
    // TO DO
    table[table_idx++] = track;
    v[track] = true;
  }
	
	public int fcfs(){
    // TO DO : Need to be changed
    for(int i=0; i<track_size; i++) {
      if(v[i]) {
        head_pos = i;
        return head_pos;
      }
    }
		while(true);
	}

	public int sstf(){
		int track_no = -1;	// TO DO : Need to be changed
    int R_dis=0, L_dis=0;
    R_dis = get_nearset_Right(head_pos);
    L_dis = get_nearset_Left(head_pos);
    
		return track_no;
  }
  
  public int get_nearset_Right(int head_pos) {
    for(int i=head_pos; i<track_size; i++) {
      if(v[i]) return i;
    }
    return -1;
  }
  public int get_nearset_Left(int head_pos) {
    for(int i=head_pos; i>=0; i--) {
      if(v[i]) return i;
    }
    return -1;
  }

	public int look(){
		int track_no = -1;	// TO DO : Need to be changed

		return track_no;
	}

	public int clook(){
		int track_no = -1;	// TO DO : Need to be changed

		return track_no;
	}

}
