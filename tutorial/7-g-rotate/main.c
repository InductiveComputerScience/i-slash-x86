#include <stdio.h>
#include <stdlib.h>

#include "test.h"

int main(){
	struct testS ts;

	ts.x = 0x0000000000000003ULL;
	ts.y = 0x3000000000000000ULL;

	test(&ts);

	printf("test(...) = %lx, %lx\n", ts.x, ts.y);

	if(ts.x == 0x1800000000000000ULL && ts.y == 0x18ULL){
		printf("success\n");
	}else{
		printf("fail\n");
	}

	return 0;
}
