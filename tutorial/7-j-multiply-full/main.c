#include <stdio.h>
#include <stdlib.h>

#include "test.h"

int main(){
	struct testS ts;

	ts.a = -1342177281;
	ts.b = 1073741825;

	test(&ts);

	printf("%d * %d\n", ts.a, ts.b);
	printf("= %ld\n", ts.x);

	if(ts.x == -1441151883174477825L){
		printf("success\n");
	}else{
		printf("fail\n");
	}

	return 0;
}
