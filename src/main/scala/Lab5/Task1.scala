package Lab5

import chisel3 . _
import chisel3 . util . _
// import chisel3.iotesters .{ ChiselFlatSpec , Driver , PeekPokeTester }
class Adder ( Width : Int ) extends Module {
require ( Width >= 0)
// your code begin from here
val io = IO(new Bundle{
    val in0 = Input (UInt(Width.W))
    val in1 =Input (UInt(Width.W))
    val sum = Output (UInt(Width.W))

})
io.sum := io.in0 + io.in1



// your code end here
}