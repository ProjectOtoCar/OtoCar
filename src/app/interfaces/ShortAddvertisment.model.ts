import { Image } from './Image';

export interface ShortAddvertisment {
    id: number;
    price: number;
    dateAdd: Date;
    active: boolean;
    title: string;
    content: string;
    car: {
        id: number;
        fuel: string;
        mileage: number;
        firstRegistartion: number;
    };
    seller: {
        id: number;
        premiumAccount: Date;
        isPremium: boolean;
    };
    images: [Image];
}
