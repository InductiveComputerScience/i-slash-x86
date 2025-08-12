#include <stdio.h>
#include <stdlib.h>

#include "test.h"

int main(){
	struct testS ts;

	ts.a = 0x0000000000000000;
	ts.b = 0x00F0000103000280;

	test(&ts);

	printf("test(...) = %lx\n", ts.y);

	if(ts.y == 0x00F0000103000280){
		printf("success\n");
	}else{
		printf("fail\n");
	}

	return 0;
}
