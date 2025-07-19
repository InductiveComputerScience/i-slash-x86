#include <stdio.h>
#include <stdlib.h>

#include "test.h"

int main(){
	struct testS ts;

	ts.c = malloc(sizeof(double) * 100);
	ts.c->x = 1.234567;

	test(&ts);

	printf("test(...) = %f\n", ts.y);

	return 0;
}
