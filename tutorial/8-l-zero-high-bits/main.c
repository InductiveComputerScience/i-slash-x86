#include <stdio.h>
#include <stdlib.h>

#include "test.h"

int main(){
	struct testS ts;

  //       --------
	ts.x = 0x00F0000103000100;
	ts.l = 32;

	test(&ts);

	printf("test(...) = %lx\n", ts.y);

	if(ts.y == 0x0000000003000100){
		printf("success\n");
	}else{
		printf("fail\n");
	}

	return 0;
}
