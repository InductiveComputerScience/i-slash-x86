#include <stdio.h>
#include <stdlib.h>

#include "test.h"

int main(){
	struct testS ts;

	ts.a = 14;
	ts.b = 3;

	test(&ts);

	printf("test(...) = %ld, %ld\n", ts.a, ts.b);

	if(ts.a == 15 && ts.b == 2){
		printf("success\n");
	}else{
		printf("fail\n");
	}

	return 0;
}
