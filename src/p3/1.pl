eltern_kind([abraham,mona],[herb,homer]).
eltern_kind([clancy,jackie],[marge,patty,selma]).
eltern_kind([homer,marge],[bart,lisa,maggie]).
eltern_kind([selma],[ling]).
maennlich([abraham,herb,homer,bart,clancy]).
weiblich([mona,marge,lisa,maggie,jackie,patty,selma,ling]).

ist_vorfahre(P1,P2):-
    eltern_kind(Eltern,Kinder),
    member(P1,Eltern),
    member(P2,Kinder).

ist_vorfahre(P1,P2):-
    eltern_kind(ElternListe,KinderListe),
    member(Eltern,ElternListe),
    member(P2,KinderListe),
    ist_vorfahre(P1,Eltern).
    
member(X, [X|_]).
member(X, [_|Tail]):-
    member(X,Tail).

sind_geschwister(P1,P2):-
    eltern_kind(_,Kinder),
    member(P1,Kinder),
    member(P2,Kinder),
    P1 \= P2.

ist_schwester_von(P1,P2):-
    sind_geschwister(P1,P2),
    weiblich(W),
    member(P1,W).

ist_bruder_von(P1,P2):-
    sind_geschwister(P1,P2),
    maennlich(M),
    member(P1,M).