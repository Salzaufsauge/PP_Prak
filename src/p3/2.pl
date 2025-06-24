
count(List, Size):-
    countAcc(List,0,Size).

countAcc([],Acc,Acc).

countAcc([_|Tail],Acc,Size):-
    NewAcc is Acc + 1,
    countAcc(Tail,NewAcc,Size).
    
    
countX(_,[],0).

countX(X,[X|Tail],Res):-
    countX(X,Tail,N),
    Res is N + 1.

countX(X,[_|Tail],Res):-
    countX(X,Tail,Res).

sum(List,Sum):-
    sumAcc(List,0,Sum).

sumAcc([],Acc,Acc).
sumAcc([X|Tail],Acc,Sum):-
    number(X),
    NewAcc is Acc + X,
    sumAcc(Tail,NewAcc,Sum).

avg(List, Avg):-
    sum(List,X),
    count(List,Y),
    Avg is X/Y.

contains(X, [X|_]).
contains(X, [_|Tail]):-
    contains(X,Tail).

element_at_index(I, N, [_|Tail]):-
    NewI is I - 1,
    element_at_index(NewI,N,Tail).

element_at_index(I, X, [X|_]):-
    I =:= 0.

remove(_, [], []).
remove(X,[X|Tail],Res):-
    remove(X,Tail,Res).
remove(X, [Y|Tail],Res):-
    Y \= X,
    remove(X,Tail,NRes),
    Res = [Y|NRes].


