#include <stdio.h>
#include <stdlib.h>

#include "test.h"

int main(){
	struct testS ts;

	ts.x = 0x123456789ABCDEF0ULL;

	test(&ts);

	printf("test(...) = %lx\n", ts.y);

	if(ts.y == 0xf0debc9a78563412ULL){
		printf("success\n");
	}else{
		printf("fail\n");
	}

	return 0;
}
