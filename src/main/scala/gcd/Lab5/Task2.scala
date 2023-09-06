package gcd.Lab5

import chisel3 . _
import chisel3 . util . _
// import chisel3 . iotesters .{ ChiselFlatSpec , Driver , PeekPokeTester }
// your code for Transaction_in class
// your code for Transaction_out class
class dataPackets [ T <: Data ]( gen : T ) extends Bundle{
    val data_field = Input(gen)
    val address_field = Input(UInt(10.W))

}
class Router [ P <: Data ]( gen1 : P ) extends Module {
    val io = IO( new Bundle{
        val in = Input(new dataPackets(gen1))
        val out = Output(new dataPackets(gen1))})
    
io.out := io.in
// your code end
}