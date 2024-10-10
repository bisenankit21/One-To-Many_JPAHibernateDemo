@OneToMany
Eager vs Lazy loading
Eager :- will retrieve everything, eager loading will load all dependent entities, load instructor and all of their courses at once
Lazy :- will retrieve on request, only load data when absolutely needed. load mainentity first and thelaod dependent entities on demand. 


Default fetch types:
@OneToOne = FetchType.EAGER
@OneToMany = FetchType.LAZY
@ManyToOne = fetchType.Eager
@ManyToMany = FetchType.LAZY



