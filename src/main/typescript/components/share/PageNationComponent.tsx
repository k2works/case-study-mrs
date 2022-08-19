import React from "react";
import "../../static/css/style.scss";

export const PageNation = (props: { handler: (e: any) => Promise<void>, pageInfo: { total: number, pageNum: number, pageSize: number, pages: number } }) => {
    return (
        <nav>
            <ul className="pagination">
                <li>
                    <a onClick={props.handler}
                       data-page={1}
                    >
                        最初
                    </a>
                </li>
                <li>
                    <a onClick={props.handler}
                       data-page={props.pageInfo.pageNum - 1}
                    >
                        前へ
                    </a>
                </li>


                <li className="active">
                    <a onClick={props.handler}
                       data-page={props.pageInfo.pageNum}
                    >
                        {props.pageInfo.pages <= props.pageInfo.pageNum ? '' : props.pageInfo.pageNum}
                    </a>
                </li>
                <li>
                    <a onClick={props.handler}
                       data-page={props.pageInfo.pageNum + 1}
                    >
                        {props.pageInfo.pages <= props.pageInfo.pageNum + 1 ? '' : props.pageInfo.pageNum + 1}
                    </a>
                </li>
                <li>
                    <a onClick={props.handler}
                       data-page={props.pageInfo.pageNum + 2}
                    >
                        {props.pageInfo.pages <= props.pageInfo.pageNum + 2 ? '' : props.pageInfo.pageNum + 2}
                    </a>
                </li>
                <li>...</li>
                <li>
                    <a onClick={props.handler}
                       data-page={props.pageInfo.pages - 2}
                    >
                        {props.pageInfo.pages - 2}
                    </a>
                </li>
                <li>
                    <a onClick={props.handler}
                       data-page={props.pageInfo.pages - 1}
                    >
                        {props.pageInfo.pages - 1}
                    </a>
                </li>
                <li>
                    <a onClick={props.handler}
                       data-page={props.pageInfo.pages}
                    >
                        {props.pageInfo.pages}
                    </a>
                </li>

                <li>
                    <a onClick={props.handler}
                       data-page={props.pageInfo.pageNum + 1}
                    >
                        次へ
                    </a>
                </li>
                <li>
                    <a onClick={props.handler}
                       data-page={props.pageInfo.pages}
                    >
                        最後
                    </a>
                </li>
            </ul>
        </nav>
    )
}
