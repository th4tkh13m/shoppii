import React from "react";
import FilterPart from "../FilterPart";
import './index.css'
import FilterAltIcon from '@mui/icons-material/FilterAltOutlined';
import Button from '@mui/material/Button';

const listFilterTitle = ["Theo thể loại", "Theo nơi bán"]

function FilterSidebar() {
    return (
        <div className="filter-sidebar">
            <div className="filter-title">
                <div className="title-icon">
                <FilterAltIcon fontSize="large"></FilterAltIcon>
                </div>
                <div className="title-body mt-1">BỘ LỌC TÌM KIẾM</div>
            </div>
            <div className="filter-body">
                {listFilterTitle.map((item) => (
                    <FilterPart title={item}></FilterPart>
                ))}

                <div className="price-range">
                    <h4>Khoảng giá</h4>
                    <div className="range d-flex align-content-center mt-5 mb-5">
                        <input className="input-price" type="text" placeholder="₫ Từ"/>
                        <p>-</p>
                        <input className="input-price" type="text" placeholder="₫ Đến"/>
                    </div>
                    <Button variant="contained" className="price-btn">ÁP DỤNG</Button>
                </div>
            </div>
        </div>
    )
}

export default FilterSidebar