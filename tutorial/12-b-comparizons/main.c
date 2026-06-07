#include <stdio.h>
#include <stdlib.h>
#include <immintrin.h>

#include "test.h"

#include "../simdlib.c"

int main(){
	struct testS ts;

	test(&ts);
	
	print_m128_s8(ts.a);
	print_m128_s8(ts.b);
	print_m128_b8(ts.c1);
	print_m128_b8(ts.c2);
	print_m128_b8(ts.c3);
	print_m128_b8(ts.c4);
	print_m128_b8(ts.c5);
	print_m128_b8(ts.c6);

	/*if(ts.y == 0x6add1e80){
		printf("success\n");
	}else{
		printf("fail\n");
	}*/

	return 0;
}




