#include <stdio.h>
#include <stdlib.h>

#include "test.h"

int main(){
	struct testS ts;

  //           <--32--><16 
  //       ____--------____
	ts.a = 0x00F0000103000280;
	ts.s = 16;
	ts.l = 32;

	test(&ts);

	printf("test(...) = %lx\n", ts.y);

	if(ts.y == 0x00010300){
		printf("success\n");
	}else{
		printf("fail\n");
	}

	return 0;
}
