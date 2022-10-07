import React from 'react'
import Typography from '@mui/material/Typography';
import Breadcrumbs from '@mui/material/Breadcrumbs';
import Link from '@mui/material/Link';
import './index.css'
import Product from '../../assets/images/bd2e86e454da37f2e6c9a128c8e9a2b8.png'
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';
import ArrowBackIosIcon from '@mui/icons-material/ArrowBackIos';
import Chip from '@mui/material/Chip';
import AddIcon from '@mui/icons-material/Add';
import RemoveIcon from '@mui/icons-material/Remove';
import Button from '@mui/material/Button';
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart';
import {StorefrontIcon} from '@mui/icons-material';

const ProductDetail = () => {
    return (
        <>
            <div className="container">
                <div className="row">
                    <div className="col-12">
                        <Breadcrumbs aria-label="breadcrumb" className='breadcrumbs-product'>
                            <Link underline="hover" color="inherit" href="/">
                                HomePage
                            </Link>
                            <Link underline="hover" color="inherit" href="/material-ui/getting-started/installation/">
                                Shop
                            </Link>
                            <Typography color="text.primary" className='bread-active'>Product</Typography>
                        </Breadcrumbs>
                    </div>
                </div>

                <div className='row page-box'>
                    <div className='col-xs-12 col-sm-6'>
                        <div className='product-images'>
                            <div id='imagesCarousel' className='carousel slide' data-bs-ride='carousel'>

                                <div className='carousel-inner'>
                                    <div className='carousel-item active'>
                                        <img src={Product} alt='img1'></img>
                                    </div>
                                    <div className='carousel-item'>
                                        <img src={Product} alt='img1'></img>
                                    </div>
                                    <div className='carousel-item'>
                                        <img src={Product} alt='img1'></img>
                                    </div>
                                </div>

                                <button className='carousel-control-prev' type="button" data-bs-target="#imagesCarousel" data-bs-slide="prev">
                                    <ArrowBackIosIcon fontSize="medium" className='text-dark next-prev-icon'></ArrowBackIosIcon>
                                </button>
                                <button className='carousel-control-next' type="button" data-bs-target="#imagesCarousel" data-bs-slide="next">
                                    <ArrowForwardIosIcon fontSize="medium" className='text-dark next-prev-icon'></ArrowForwardIosIcon>
                                </button>
                            </div>
                        </div>

                        <div className='small-images'>
                            <img src={Product} alt='img1'></img>
                            <img src={Product} alt='img1'></img>
                            <img src={Product} alt='img1'></img>
                            <img src={Product} alt='img1'></img>
                        </div>
                    </div>

                    <div className='col-xs-12 col-sm-6'>
                        <div className='detail'>
                            <p>Sản phẩm gì gì đó</p>
                            <Chip label="Thời trang" className='catalog'/>
                            <p>₫100.000</p>
                            <div className='quantity'>
                                <AddIcon fontSize='large'></AddIcon>
                                <input value="1"></input>
                                <RemoveIcon fontSize='large'></RemoveIcon>
                            </div>
                            {/* <table className='detail-table'>
                                <tr>
                                    <th>Thể loại</th>
                                    <td>
                                        <Chip label="Thời trang" className='catalog'/>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Giá</th>
                                    <td>
                                        <p>₫100.000</p>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Số lượng</th>
                                    <td>
                                        <div className='quantity'>
                                            <AddIcon fontSize='large'></AddIcon>
                                            <input value="1"></input>
                                            <RemoveIcon fontSize='large'></RemoveIcon>
                                        </div>
                                    </td>
                                </tr>
                            </table> */}
                        </div>
                        <Button 
                            variant="outlined" 
                            startIcon={<AddShoppingCartIcon fontSize='large'/>} 
                            className="add-cart-btn"
                        >
                            Thêm vào giỏ hàng
                        </Button>
                    </div>
                </div>

                {/* <hr></hr> */}

                <div className='row page-box'>
                    <div className='col-12'>
                        <div className='shop'>
                            <div className='shop-image'>
                                <img src={Product} alt='img1'></img>
                            </div>
                            <div className='shop-info'>
                                <p>Cửa hàng nào đó</p>
                                <Button 
                                    variant="outlined" 
                                    startIcon={<StorefrontIcon fontSize='medium'/>} 
                                    className="view-shop-btn"
                                >
                                    Xem cửa hàng
                                </Button>
                            </div>
                        </div>
                    </div>
                </div>

                {/* <hr></hr> */}

                <div className='row page-box'>
                    <div className='col-xs-12 col-xs-8'>
                        <div className='product-decs'>
                            <p>Mô tả sản phẩm</p>
                            <div className='product-decs-detail'>
                                <textarea>
                                🌈 Bảng kích thước áo Polo lỡ unisex:
                                Size M : dài 63cm, rộng 47cm , 40-59 kg, cao 1m50 – 1m60
                                Size L : dài 68cm, rộng 51cm,  60 - 70 kg, cao 1m61 – 1m70
                                Size XL : dài 72cm, rộng 57cm,  71 - 85 kg, cao 1m71 – 1m80
                                mọi người ưu tiên chọn size theo chiều cao nhé
                                </textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default ProductDetail
