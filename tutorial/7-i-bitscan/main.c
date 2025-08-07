#include <stdio.h>
#include <stdlib.h>

#include "test.h"

int main(){
	struct testS ts;

  //                 .
	ts.x = 0x0000001000100000ULL;
  //        .
	ts.y = 0x0100000000010000ULL;

	test(&ts);

	printf("test(...) = %ld, %ld\n", ts.x, ts.y);

	if(ts.x == 20 && ts.y == 56){
		printf("success\n");
	}else{
		printf("fail\n");
	}

	return 0;
}
