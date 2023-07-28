#include <stdio.h>
#include <stdint.h>
#include <inttypes.h>
#include <wchar.h>
#include <stdlib.h>
#include <string.h>
#include <sys/time.h>
#include <time.h>
#include <immintrin.h>

#include "toupper.h"

void ToUpper(char *str, size_t len);
void ToUpperBL(char *str, size_t len);
void ToUpperBL2(char *str, size_t len);

void FillWithRandomData(char *str, size_t len){
	for(uint64_t i = 0; i < len; i++){
		struct RdrandGuaranteedS r;
		RdrandGuaranteed(&r);
		if(r.n % 2 == 1){
			// Upper
			str[i] = 'A' + (r.n / 10) % (26 + 5);
		}else{
			// Lower
			str[i] = 'a' + (r.n / 10) % (26 + 5);
		}
	}
}

int main(){
	size_t len;
	char *str;
	struct timespec start, end;
	uint64_t delta_us;

	// Fill with random data
	len = 10000000;
	str = aligned_alloc(32, 8+len + 100);
	str += 32 - 8;
	*(uint64_t*)str = len;
	str += 8;

	FillWithRandomData(str, len);
	clock_gettime(CLOCK_MONOTONIC_RAW, &start);
	ToUpper(str, len);
	clock_gettime(CLOCK_MONOTONIC_RAW, &end);
	delta_us = (end.tv_sec - start.tv_sec) * 1000000 + (end.tv_nsec - start.tv_nsec) / 1000;
	printf("ToUpper (plain C -O3 AVX2): %lu us\n", delta_us);

	FillWithRandomData(str, len);
	clock_gettime(CLOCK_MONOTONIC_RAW, &start);
	ToUpperBL(str, len);
	clock_gettime(CLOCK_MONOTONIC_RAW, &end);
	delta_us = (end.tv_sec - start.tv_sec) * 1000000 + (end.tv_nsec - start.tv_nsec) / 1000;
	printf("ToUpperBL (C branchless v1 -O3 AVX2): %lu us\n", delta_us);

	FillWithRandomData(str, len);
	clock_gettime(CLOCK_MONOTONIC_RAW, &start);
	ToUpperBL2(str, len);
	clock_gettime(CLOCK_MONOTONIC_RAW, &end);
	delta_us = (end.tv_sec - start.tv_sec) * 1000000 + (end.tv_nsec - start.tv_nsec) / 1000;
	printf("ToUpperBL2 (C branchless v2 -O3 AVX2): %lu us\n", delta_us);

	FillWithRandomData(str, len);
	clock_gettime(CLOCK_MONOTONIC_RAW, &start);
	struct ToUpperILS tuil;
	tuil.str = (__m256*)str;
	ToUpperIL(&tuil);
	clock_gettime(CLOCK_MONOTONIC_RAW, &end);
	delta_us = (end.tv_sec - start.tv_sec) * 1000000 + (end.tv_nsec - start.tv_nsec) / 1000;

	printf("ToUpper (I/x86 using AVX2): %lu us\n", delta_us);

	return 0;
}





















