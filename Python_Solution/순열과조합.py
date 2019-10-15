def comb(n, r, q):
    if r==0:
        print(T)
    elif n<r:
        return
    # elif n==0:
    #     return   중복조합
    else:
        T[r-1] = a[n-1]
        comb(n-1, r-1, q)
        # comb(n, r-1, q)  중복조합
        comb(n-1, r, q)

a=[1,2,3,4]
T=[0]*3
comb(4,3,3)