import React from "react";
import {useDispatch, useSelector} from "react-redux";
import {increment, selectCount} from "../features/counterSlice";

interface IProps {
    name: string;
}

interface IState {
    count: number;
}

export const SubComponent: React.FC<IProps> = (props) => {
    const count = useSelector(selectCount);
    const dispatch = useDispatch();

    return (
        <div>
            <h2>{props.name}</h2>
            <div>{count}</div>
            <button onClick={() => dispatch(increment())}>Add +1</button>
        </div>
    );
};
