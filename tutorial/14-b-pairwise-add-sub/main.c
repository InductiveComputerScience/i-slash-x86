#include <stdio.h>
#include <stdlib.h>
#include <immintrin.h>

#include "test.h"

#include "../simdlib.c"

int main(){
	struct testS ts;

	test(&ts);

	print_m128_s16(ts.a);
	print_m128_s16(ts.b);
	print_m128_s16(ts.x);
	print_m128_s16(ts.y);

	/*if(ts.y == 0x6add1e80){
		printf("success\n");
	}else{
		printf("fail\n");
	}*/

	return 0;
}


