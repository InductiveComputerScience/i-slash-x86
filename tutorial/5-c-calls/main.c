#include <stdio.h>
#include <stdlib.h>

#include "test.h"

int main(){
	struct testS ts;

	ts.fns = malloc(sizeof(struct fnS));
	ts.x = 10;

	test(&ts);

	printf("test(...) = %ld\n", ts.y);

	return 0;
}
