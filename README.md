# I/x86

A programming language that gives direct control of x86 processors. It is translated directly into x86 assembly. It has access to all features of x86-64 processors.

## Examples

### Finding the index of a character in a string
```
Fnc IndexOfCharacter
  Mov found, false
  Mov i, 0
  Loop
    Len len, string
  If exp b: i < len & !found
    Idr c, string, i
    If exp b: c = character
      Mov found, true
      Acw indexReference, numberValue, i
    Endb
    Inc i
  EndLoop
  Mov retval, found
Ret
```

The input, output and local variables are given in a separate structure.

```
Bgs NumberReference
  u64 numberValue
Ens

Bgs IndexOfCharacterS
  u16a string
  u16 character
  u16 c
  str NumberReference indexReference
  u8 retval
  u64 i
  b1 found
  u64 len
  b1 tb10
  b1 tb11
  b1 tb12
Ens
```

A header for C is also generated, so that you can call the functions from C or C++.



## Rdrand

```
Bgs RdrandGuaranteedS
  u64 n
  b1 c
  b1 tb10
Ens

Fnc RdrandGuaranteed
  Mov c, 0
  Loop
  If exp b: c = 0
    Rdrand n, c
  EndLoop
Ret
```


