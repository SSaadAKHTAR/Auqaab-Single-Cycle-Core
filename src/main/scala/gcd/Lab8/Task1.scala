package Lab8
import chisel3 . _
import chisel3 . util . _


class memory_assignment extends Module {
val io = IO (new Bundle {
val memory_out = Vec (4 , Output ( UInt (32. W ) ) )
val requestor = Vec (4 , Flipped ( Decoupled ( UInt (32. W ) ) ) )
val Readaddr = Input ( UInt (5. W ) )
val Writeaddr = Input ( UInt (5. W ) )
})

val arbibbay = Module(new RRArbiter( UInt(32.W), 4))
 arbibbay.io.out.ready := 1.B
io.memory_out(0) := 0.U
io.memory_out(1) := 0.U
io.memory_out(2) := 0.U
io.memory_out(3) := 0.U
val que = Module(new Queue(UInt(32.W) , 4))
val que2 = Module(new Queue(UInt(32.W) , 4))
val que1 = Module(new Queue(UInt(32.W) , 4))
val que3 = Module(new Queue(UInt(32.W) , 4))

que.io.enq <> io.requestor(0)
que1.io.enq <> io.requestor(1)
que2.io.enq <> io.requestor(2)
que3.io.enq <> io.requestor(3)


val membank = Mem(4 , UInt(32.W))

arbibbay.io.in(0) <> que.io.deq
arbibbay.io.in(1) <> que1.io.deq
arbibbay.io.in(2) <> que2.io.deq
arbibbay.io.in(3) <> que3.io.deq


switch(io.Readaddr){

    is(0.U){
        io.memory_out(0) := membank.read(io.Readaddr)
         
    }
    is(1.U){
        io.memory_out(1) := membank.read(io.Readaddr)
    }
    is(2.U){
        io.memory_out(2) := membank.read(io.Readaddr)
    }
    is(3.U){
        io.memory_out(3) := membank.read(io.Readaddr)
    }

}

    membank.write(io.Writeaddr , arbibbay.io.out.bits)

// Start your code from here
// End your code here
}