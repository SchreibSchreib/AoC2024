package Interfaces;

import java.io.Serializable;

public interface InputManipulatable<T> extends SizeGettable {

    public T getFormattedInput();
}
