#include <stdio.h>

#include "test.h"

int main(){
	struct testS ts;

	ts.x = -21;

	test(&ts);

	printf("a = %d\n", ts.a);
	printf("b = %f\n", ts.b);
	printf("test(%d) = %f\n", ts.x, ts.y);

	return 0;
}
