#include <stdio.h>
#include <stdlib.h>
#include <immintrin.h>

#include "test.h"

#include "../simdlib.c"

int main(){
	struct testS ts;

	test(&ts);

	print_m128_u64(ts.a);
	print_m128_u64(ts.b);
	
	print_m128_b128(ts.x);
	print_m128_b128(ts.y);
	print_m128_b128(ts.z1);
	print_m128_b128(ts.z2);
	print_m128_b128(ts.z3);
	print_m128_b128(ts.z4);
	print_m128_b128(ts.z5);
	print_m128_b128(ts.z6);
	print_m128_b128(ts.z7);

	/*if(ts.y == 0x6add1e80){
		printf("success\n");
	}else{
		printf("fail\n");
	}*/

	return 0;
}

