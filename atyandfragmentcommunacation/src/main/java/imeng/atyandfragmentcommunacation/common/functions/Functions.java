package imeng.atyandfragmentcommunacation.common.functions;

import java.util.HashMap;

/**
 * 各种方法集合的类，可以把一个方法类以key-value的形式放入本类, 可以通过key值来调用相应的方法.
 *
 * @author Administrator
 * @date 2016/4/11 11:08
 * @version :
 */
public class Functions {
    // 带参数方法的集合，key值为方法的名字
    private HashMap mFunctionWithParam;
    // 无参数无返回值的方法集合，同理key值为方法名字
    private HashMap<String, Function> mFunctionNoParamAndResult;

    /**
     * 基础方法类
     */
    public static abstract class Function {
        // 方法的名字，用来做调用，也可以理解为方法的指针
        public String mFunctionName;

        public Function(String functionName) {
            this.mFunctionName = functionName;
        }
    }

    /**
     * 带有参数没有返回值的方法
     */
    public static abstract class FunctionWithParam extends Function {

        public FunctionWithParam(String functionName) {
            super(functionName);
        }

        public abstract void function(Param param);
    }

    /**
     * 没有参数和返回值的方法
     */
    public static abstract class FunctionNoParamAndResult extends Function {
        public FunctionNoParamAndResult(String functionName) {
            super(functionName);
        }

        public abstract void function();
    }

    /**
     * * 添加带参数的函数
     * 
     * @param function {<a href="http://www.jobbole.com/members/57845349">@link</a>
     *        com.niu.myapp.myapp.view.util.Functions.FunctionWithParam}
     * @return
     */
    public Functions addFunction(FunctionWithParam function) {
        if (function == null) {
            return this;
        }
        if (mFunctionWithParam == null) {
            mFunctionWithParam = new HashMap(1);
        }

        mFunctionWithParam.put(function.mFunctionName, function);
        return this;
    }

    /**
     * 添加带返回值的函数
     * 
     * @param function {<a href="http://www.jobbole.com/members/57845349">@link</a>
     *        com.niu.myapp.myapp.view.util.Functions.FunctionWithResult}
     * @return
     */
    public Functions addFunction(FunctionNoParamAndResult function) {
        if (function == null) {
            return this;
        }
        if (mFunctionNoParamAndResult == null) {
            mFunctionNoParamAndResult = new HashMap(1);
        }
        mFunctionNoParamAndResult.put(function.mFunctionName, function);
        return this;
    }

    /**
     * * 根据函数名，回调无参无返回值的函数
     * 
     * @param funcName
     */
    public void invokeFunc(String funcName) throws FunctionException {
        FunctionNoParamAndResult f = null;
        if (mFunctionNoParamAndResult != null) {
            f = (FunctionNoParamAndResult) mFunctionNoParamAndResult.get(funcName);
            if (f != null) {
                f.function();
            }
        }
        if (f == null) {
            throw new FunctionException("没有此函数");
        }
    }

    /**
     * * 调用具有参数的函数
     * 
     * @param funcName
     * @param param
     * @param
     */
    public void invokeFunc(String funcName, Param param) throws FunctionException {
        FunctionWithParam f = null;
        if (mFunctionWithParam != null) {
            f = (FunctionWithParam) mFunctionWithParam.get(funcName);
            if (f != null) {
                f.function(param);
            }
        }
    }

}
