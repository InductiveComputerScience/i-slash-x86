#include <stdio.h>
#include <stdlib.h>

#include "test.h"

int main(){
	struct testS ts;

	test(&ts);

	printf("test(...) = %ld\n", ts.y);

	return 0;
}
