#include <stdio.h>

#include "test.h"

int main(){
	struct testS ts;

	ts.x = 21;

	test(&ts);

	printf("test(%ld) = %ld\n", ts.x, ts.y);

	return 0;
}
