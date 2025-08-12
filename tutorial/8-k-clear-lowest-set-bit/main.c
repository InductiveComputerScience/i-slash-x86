#include <stdio.h>
#include <stdlib.h>

#include "test.h"

int main(){
	struct testS ts;

	ts.x = 0x00F0000103000100;

	test(&ts);

	printf("test(...) = %lx\n", ts.y);

	if(ts.y == 0x00F0000103000000){
		printf("success\n");
	}else{
		printf("fail\n");
	}

	return 0;
}
