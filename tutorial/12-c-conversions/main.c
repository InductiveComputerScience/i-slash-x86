#include <stdio.h>
#include <stdlib.h>
#include <immintrin.h>

#include "test.h"

#include "../simdlib.c"

int main(){
	struct testS ts;

	test(&ts);
	
	print_m128_f64(ts.a);
	print_m128_f32(ts.b);
	print_m128_f64(ts.a2);
	print_m128_f32(ts.c);
	
	print_m128_f32(ts.d);
	print_m128_f16(ts.e);
	print_m128_f32(ts.d2);
	print_m128_f16(ts.f);
	
	print_m128_f64(ts.h);
	print_m128_f64(ts.i);

	print_m128_b8(ts.x);
	print_m128_b32(ts.y);	
	print_m128_b128(ts.z);

	/*if(ts.y == 0x6add1e80){
		printf("success\n");
	}else{
		printf("fail\n");
	}*/

	return 0;
}







