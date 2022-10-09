import React from "react";
import './index.css'
import OrderHistoryItem from "../OrderHistoryItem";

function OrderHistory() {
    return (
        <div className="order-history">
            {[0,1,2].map(item => (
                <OrderHistoryItem/>
            ))}
        </div>
    )
}

export default OrderHistory