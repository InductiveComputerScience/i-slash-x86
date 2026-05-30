#include <stdio.h>
#include <stdlib.h>
#include <immintrin.h>

#include "test.h"

void print_m128_u32(__m128 reg);


int main(){
	struct testS ts;

	//ts.x = _mm_setr_epi32(1, 2, 3, 4);
	//ts.y = _mm_setr_epi32(1, 2, 3, 4);

	//printf("test(...) = %x, %x\n", ts.y, ts.x);
	//print_m128_u32(ts.x);
	//print_m128_u32(ts.y);

	test(&ts);

	//printf("test(...) = %x, %x\n", ts.y, ts.x);
	print_m128_u32(ts.x);
	print_m128_u32(ts.y);

	/*if(ts.y == 0x6add1e80){
		printf("success\n");
	}else{
		printf("fail\n");
	}*/

	return 0;
}

void print_m128_u32(__m128 reg) {
    // Reinterpret the 128-bit SIMD register as an array of four uint32_t values
    uint32_t *values = (uint32_t *)&reg;
    
    // Print in standard element order (from index 0 to 3)
    printf("u32 values: %u, %u, %u, %u\n", 
           values[0], values[1], values[2], values[3]);
}
