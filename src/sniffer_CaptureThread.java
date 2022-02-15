import javax.swing.SwingUtilities

public abstract class sniffer_CaptureThread {
    //Globals
    private Object value;
    private Thread thread;

    //Nested class maintains ref to current thread under separate sync control
    private static class ThreadVar
    {
        private Thread thread;
        ThreadVar (Thread t)
        {
            thread = t;
        }
        synchronized Thread get()
        {
            return thread;
        }

        synchronized void clear()
        {
            thread = null;
        }
    }

    private ThreadVar threadVar;

    protected synchronized  Object getValue()
    {
        return value;
    }

    private synchronized void setValue(Object x)
    {
        value = x;
    }

    public abstract Object construct();

    public void finished()
    {

    }
    public void interrupt()
    {
        Thread t = threadVar.get();

        if(t != null)
        {
            t.interrupt();
        }

        threadVar.clear();
    }
    public Object get()
    {
        while(true)
    }
}
