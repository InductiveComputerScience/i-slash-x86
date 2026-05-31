#include <stdio.h>
#include <stdlib.h>
#include <immintrin.h>

#include "test.h"

void print_m128_u32(__m128 reg);
void print_m128_u8(__m128 reg);


int main(){
	struct testS ts;

	test(&ts);

	print_m128_u32(ts.x);
	print_m128_u32(ts.y);
	print_m128_u8(ts.a);
	print_m128_u8(ts.b);
	print_m128_u8(ts.z);

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

void print_m128_u8(__m128 reg) {
    // Reinterpret the 128-bit SIMD register as an array of four uint8_t values
    uint8_t *values = (uint8_t *)&reg;
    
    printf("u8 values: %u, %u, %u, %u, %u, %u, %u, %u, %u, %u, %u, %u, %u, %u, %u, %u\n", values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9], values[10], values[11], values[12], values[13], values[14], values[15]);
}

