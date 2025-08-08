#include <stdio.h>
#include <stdlib.h>

#include "test.h"

int main(){
	struct testS ts;

	ts.x = 1;
	ts.y = 0xFFFFFFFF;

	printf("test(...) = %x, %x\n", ts.y, ts.x);

	test(&ts);

	printf("test(...) = %x, %x\n", ts.y, ts.x);

	if(ts.y == 0x6add1e80){
		printf("success\n");
	}else{
		printf("fail\n");
	}

	return 0;
}
