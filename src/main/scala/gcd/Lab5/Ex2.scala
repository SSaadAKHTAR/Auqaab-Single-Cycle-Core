package gcd.Lab5

import chisel3 . _
import chisel3 . util . _

class IOBundle [ T <: Data ]( dtype : T ) extends Bundle{
   
val out = Output ( dtype )
val in1 = Input ( dtype )
val in2 = Input ( dtype )
val sel = Input ( Bool () )

}


class eMux(dtype:UInt)  extends Module {
val io = IO (new IOBundle(dtype)) 
// val out = Output ( gen )
// val in1 = Input ( gen )
// val in2 = Input ( gen )
// val sel = Input ( Bool () )
// })
io . out := Mux2_to_1 ( io . in2 , io . in1 , io . sel )
def Mux2_to_1 [ T <: Data ]( in_0 :T , in_1 :T , sel : Bool ) : T = {
Mux( sel , in_1 , in_0 )
}


}