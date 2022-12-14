import React from 'react'
import Typography from '@mui/material/Typography'
import Breadcrumbs from '@mui/material/Breadcrumbs'
import Link from '@mui/material/Link'
import './index.css'
import ProductImage from '../../assets/images/bd2e86e454da37f2e6c9a128c8e9a2b8.png'
import Chip from '@mui/material/Chip'
import Button from '@mui/material/Button'
import {
    Storefront,
    ArrowForwardIos,
    ArrowBackIos,
    Add,
    Remove,
    AddShoppingCart,
} from '@mui/icons-material'

const ProductDetail = () => {
    return (
        <>
            <div className="container">
                <div className="row">
                    <div className="col-12">
                        <Breadcrumbs
                            aria-label="breadcrumb"
                            className="breadcrumbs-product"
                        >
                            <Link underline="hover" color="inherit" href="/">
                                HomePage
                            </Link>
                            <Link
                                underline="hover"
                                color="inherit"
                                href="/material-ui/getting-started/installation/"
                            >
                                Shop
                            </Link>
                            <Typography
                                color="text.primary"
                                className="bread-active"
                            >
                                Product
                            </Typography>
                        </Breadcrumbs>
                    </div>
                </div>

                <div className="row page-box">
                    <div className="col-xs-12 col-sm-6">
                        <div className="product-images">
                            <div
                                id="imagesCarousel"
                                className="carousel slide"
                                data-bs-ride="carousel"
                            >
                                <div className="carousel-inner">
                                    <div className="carousel-item active">
                                        <img
                                            src={ProductImage}
                                            alt="img1"
                                        ></img>
                                    </div>
                                    <div className="carousel-item">
                                        <img
                                            src={ProductImage}
                                            alt="img1"
                                        ></img>
                                    </div>
                                    <div className="carousel-item">
                                        <img
                                            src={ProductImage}
                                            alt="img1"
                                        ></img>
                                    </div>
                                </div>

                                <button
                                    className="carousel-control-prev"
                                    type="button"
                                    data-bs-target="#imagesCarousel"
                                    data-bs-slide="prev"
                                >
                                    <ArrowBackIos
                                        fontSize="medium"
                                        className="text-dark next-prev-icon"
                                    ></ArrowBackIos>
                                </button>
                                <button
                                    className="carousel-control-next"
                                    type="button"
                                    data-bs-target="#imagesCarousel"
                                    data-bs-slide="next"
                                >
                                    <ArrowForwardIos
                                        fontSize="medium"
                                        className="text-dark next-prev-icon"
                                    ></ArrowForwardIos>
                                </button>
                            </div>
                        </div>

                        <div className="small-images">
                            <img src={ProductImage} alt="img1"></img>
                            <img src={ProductImage} alt="img1"></img>
                            <img src={ProductImage} alt="img1"></img>
                            <img src={ProductImage} alt="img1"></img>
                        </div>
                    </div>

                    <div className="col-xs-12 col-sm-6">
                        <div className="detail">
                            <p>S???n ph???m g?? g?? ????</p>
                            <Chip label="Th???i trang" className="catalog" />
                            <p>???100.000</p>
                            <div className="quantity">
                                <Add fontSize="large"></Add>
                                <input value="1"></input>
                                <Remove fontSize="large"></Remove>
                            </div>
                            {/* <table className='detail-table'>
                                <tr>
                                    <th>Th??? lo???i</th>
                                    <td>
                                        <Chip label="Th???i trang" className='catalog'/>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Gi??</th>
                                    <td>
                                        <p>???100.000</p>
                                    </td>
                                </tr>
                                <tr>
                                    <th>S??? l?????ng</th>
                                    <td>
                                        <div className='quantity'>
                                            <Add fontSize='large'></Add>
                                            <input value="1"></input>
                                            <Remove fontSize='large'></Remove>
                                        </div>
                                    </td>
                                </tr>
                            </table> */}
                        </div>
                        <Button
                            variant="outlined"
                            startIcon={<AddShoppingCart fontSize="large" />}
                            className="add-cart-btn"
                        >
                            Th??m v??o gi??? h??ng
                        </Button>
                    </div>
                </div>

                {/* <hr></hr> */}

                <div className="row page-box">
                    <div className="col-12">
                        <div className="shop">
                            <div className="shop-image">
                                <img src={ProductImage} alt="img1"></img>
                            </div>
                            <div className="shop-info">
                                <p>C???a h??ng n??o ????</p>
                                <Button
                                    variant="outlined"
                                    startIcon={<Storefront fontSize="medium" />}
                                    className="view-shop-btn"
                                >
                                    Xem c???a h??ng
                                </Button>
                            </div>
                        </div>
                    </div>
                </div>

                {/* <hr></hr> */}

                <div className="row page-box">
                    <div className="col-xs-12 col-xs-8">
                        <div className="product-decs">
                            <p>M?? t??? s???n ph???m</p>
                            <div className="product-decs-detail">
                                <textarea>
                                    ???? B???ng k??ch th?????c ??o Polo l??? unisex: Size M
                                    : d??i 63cm, r???ng 47cm , 40-59 kg, cao 1m50 ???
                                    1m60 Size L : d??i 68cm, r???ng 51cm, 60 - 70
                                    kg, cao 1m61 ??? 1m70 Size XL : d??i 72cm, r???ng
                                    57cm, 71 - 85 kg, cao 1m71 ??? 1m80 m???i ng?????i
                                    ??u ti??n ch???n size theo chi???u cao nh??
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
