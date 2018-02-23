grammar Xquery;
WS  : [ \t\r\n]+ ->skip;

ap  : 'doc(' FileName ')' '/' rp
    | 'doc(' FileName ')' '//' rp
    ;

rp  : TagName
    | '*' 
    | '.' 
    | '..' 
    | 'text()' 
    | '@' AttName
    | '(' rp ')'
    | rp '/' rp 
    | rp '/''/' rp 
    | rp '[' f ']' 
    | rp ',' rp
    ;

f   : rp 
    | rp '=' rp 
    | rp 'eq' rp 
    | rp '==' rp 
    | rp 'is' rp
    | '('f')' 
    | f 'and' f 
    | f 'or' f 
    | 'not' f
    ;

xq  : Var
    | StringConstant
    | ap
    | '(' xq ')'
    | xq ',' xq
    | xq '/' rp
    | xq '//' rp
    | '<' TagName '>''{' xq '}''<''/' TagName '>'
    | forClause letClause? whereClause? returnClause
    | letClause xq
    ;

forClause   : 'for' Var 'in' xq inClause*;
inClause    : ',' Var 'in' xq;

letClause  : 'let' Var ':=' xq eqClause*;
eqClause    : ',' Var ':=' xq;

whereClause : 'where' cond;

returnClause    : 'return' xq;

cond    : xq '=' xq
        | xq 'eq' xq
        | xq '==' xq
        | xq 'is' xq
        | 'empty(' xq ')'
        | 'some' Var 'in' xq inClause* 'satisfies' cond
        | '(' cond ')'
        | cond 'and' cond
        | cond 'or' cond
        | 'not' cond
        ;

TagName : [_.a-zA-Z0-9]+;

Var : [_.a-zA-Z0-9]+;

StringConstant : [_.a-zA-Z0-9]+;

FileName : '"'[_.a-zA-Z0-9]+'"';

AttName : '"'[_.a-zA-Z0-9]+'"';
