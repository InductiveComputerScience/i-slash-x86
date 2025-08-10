#include <stdio.h>
#include <stdlib.h>

#include "test.h"

int main(){
	struct testS ts;

	test(&ts);

	printf("test(...) = %d, %lu\n", ts.c, ts.y);

	return 0;
}
