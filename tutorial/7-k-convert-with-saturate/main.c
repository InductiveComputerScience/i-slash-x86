#include <stdio.h>
#include <stdlib.h>

#include "test.h"

int main(){
	struct testS ts;

	ts.a = 100000000;

	test(&ts);

	printf("%d\n", ts.a);
	printf("= %d\n", ts.x);

	if(ts.x == 65535){
		printf("success\n");
	}else{
		printf("fail\n");
	}

	return 0;
}
