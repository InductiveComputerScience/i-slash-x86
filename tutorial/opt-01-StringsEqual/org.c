#include <stdint.h>
#include <wchar.h>
#include <stdbool.h>

int StringsEqual(wchar_t *a, size_t aLength, wchar_t *b, size_t bLength){
  int equal;
  double i;

  equal = true;
  if((double)aLength == (double)bLength){
    for(i = 0.0; i < (double)aLength && equal; i = i + 1.0){
      if(a[(int)(i)] != b[(int)(i)]){
        equal = false;
      }
    }
  }else{
    equal = false;
  }

  return equal;
}
